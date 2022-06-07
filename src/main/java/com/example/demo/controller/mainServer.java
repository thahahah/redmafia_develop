package com.example.demo.controller;

import org.springframework.stereotype.Service;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//내부에서 로직 처리할 때 사용 bean 등록할 때 사용
@Service
// 나중에 만들때 /index/{방이름} 이런식으로? 만들수있으면
@ServerEndpoint(value = "/red_mafia")
public class mainServer {

    //닉네임 가져옴
    controller control = new controller();
    String nickname = control.nickname1;

    //순서가 있는 데이터 집합, 데이터 중복 허용 세션 리스트 생성
    static List<Session> list = Collections.synchronizedList(new ArrayList<>());
    //Collections.synchronizedList는 session에 동기화 되지 않은 접근을 방지하기 위해 사용

    //websocket으로 브라우저가 접속하면 요청되는 함수
    @OnOpen
    public void onOpen(Session session) {
        System.out.println("입장");
        //세션 ID 넣음
        list.add(session);
    }

    //websocket으로 메세지가 오면 요청되는 함수
    @OnMessage
    public void onMessage(Session session, String message) {
        //System.out.println(nickname);
        for (Session value : list) {
            // getId()로 session ID와 list의 session ID를 가져와 비교해 세션 id가 같으면
            if (session.getId().equals(value.getId())) {
                try {
                    // 텍스트 메세지 전송
                    // getBasicRemote는 초반에 설정한 endpoint의 value값에 텍스트 메세지 전송
                    // get(i)는 리스트 i번째
                    System.out.println(nickname + " : " + message);
                    value.getBasicRemote().sendText(nickname + " : " + message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    // 아닐경우
                    System.out.println(nickname + message);
                    value.getBasicRemote().sendText(nickname + " : " + message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    //websocket과 브라우저가 접속이 끊기면 요청되는 함수
    @OnClose
    public void onClose(Session session) throws IOException {
        System.out.println("닫힘");
        //session 닫기
        session.close();
    }

    //websocket과 브라우저 간에 통신에러가 발생하면 요청되는 함수
    @OnError
    public void onError(Throwable t){
        System.out.println("오류");
        t.printStackTrace();
    }

}
