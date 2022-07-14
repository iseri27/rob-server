package com.xjtu.dbc.robserver.websocket.impl;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xjtu.dbc.robserver.common.Constants;
import com.xjtu.dbc.robserver.common.Utils;
import com.xjtu.dbc.robserver.message.dao.MessageDao;
import com.xjtu.dbc.robserver.message.entity.MessageDto;
import com.xjtu.dbc.robserver.websocket.SocketIOService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yjq
 * @version 1.0
 * @date 2022/7/11 15:30
 */
@Slf4j
@Service
public class SocketIOServiceImpl implements SocketIOService {

    // 存放已连接的客户端
    private static Map<String, SocketIOClient> clientMap = new ConcurrentHashMap<>();

    @Autowired
    private SocketIOServer socketIOServer;

    @Resource
    private MessageDao messageDao;

    /**
     * Spring IoC 容器创建之后, 在加载 SocketIOServiceImpl Bean 之后启动
     */
    @PostConstruct
    private void autoStartup() {
        start();
    }

    /**
     * Spring IoC 容器在销毁 SocketIOServiceImpl Bean 之前关闭
     * 避免重启项目服务端口占用问题
     */
    @PreDestroy
    private void autoStop() {
        stop();
    }

    @Override
    public void start() {
        //监听客户端连接
        socketIOServer.addConnectListener(client -> {
            String userid = getParamsByClient(client, "userid");
            clientMap.put(userid, client);
            log.info("新建客户端连接: " + Utils.getIpByClient(client) + ", 用户名 :" + userid);
        });

        // 监听客户端断开连接
        socketIOServer.addDisconnectListener(client -> {
            String userid = getParamsByClient(client, "userid");
            clientMap.remove(userid);
            log.info("用户 " + userid + " 已经断开连接");
        });
        // 监听客户端发送消息
        socketIOServer.addEventListener(Constants.EVENT_MESSAGE_TO_SERVER, MessageDto.class, (client, data, ackSender) -> {
            String sender_id = getParamsByClient(client, "userid");
            ObjectMapper mapper = new ObjectMapper();
            MessageDto message = data;
            log.info(message.toString());
            sendMessageToFriend(message.getReceiverid() + "", message);
        });

        // 启动服务
        socketIOServer.start();
    }


    @Override
    public void stop() {
        if (socketIOServer != null) {
            socketIOServer.stop();
            socketIOServer = null;
        }
    }

    /**
     * 发送信息给指定用户
     *
     * @param receiver_id
     * @param msg
     */
    private void sendMessageToFriend(String receiver_id, MessageDto msg) {
        log.info(clientMap.get(receiver_id) + "");
        SocketIOClient receiver_client = clientMap.get(receiver_id);
        msg.setMsgtype(Constants.MSG_CHAT);
        if (receiver_client != null) {
            msg.setMsgstatus(1);//设置消息为已读
            messageDao.insertMessage(msg);
            receiver_client.sendEvent(Constants.EVENT_MESSAGE_SEND, msg);
        } else {
            msg.setMsgstatus(0);//设置消息为未读
            messageDao.insertMessage(msg);
        }
    }

    /**
     * 获取客户端 url 中的参数
     *
     * @param client: 客户端
     * @return: 获取的参数
     */
    private String getParamsByClient(SocketIOClient client, String key) {
        Map<String, List<String>> params = client.getHandshakeData().getUrlParams();

        System.out.println(params.toString());

        List<String> userIdList = params.get(key);
        if (!CollectionUtils.isEmpty(userIdList)) {
            return userIdList.get(0);
        }
        return null;
    }

}
