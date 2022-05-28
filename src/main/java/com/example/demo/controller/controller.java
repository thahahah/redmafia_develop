package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

//import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@Controller
public class controller {
    // 다른 파일에서 사용하기 위해 정적(static)사용
    static String nickname1 = "";

    String query = "SELECT * FROM test WHERE id = ?";
    String query1 = "INSERT INTO test(id) VALUES (?)";
    String query2 = "DELETE FROM test WHERE id = ? ";

    ResultSet resultSet; // db명령을 dbms에 전달하고 그 출력결과를 가져오는 인터페이스

/*
    public static void main(String[] args){
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://database-1.caxyf4kvshky.ap-northeast-2.rds.amazonaws.com:3306/capstone",
                    "admin", "zoqtmxhs12"); // sql에 연결

            Statement statement = connection.createStatement(); // sql문 실행하게하는 객체


            //ResultSet resultSet = statement.executeQuery("select * from test");

            //while (resultSet.next()) {
           //     System.out.println(resultSet.getString("id"));
            //}

        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
*/

    // 닉네임 중복 검사 및 추가
    @PostMapping("/index/create")
    public String dbname(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException { //http 요청을 객체로 반환해 가져옴
        //HttpServletRequest는 값을 받아올 수 있다
        //HttpServletResponse는 HttpServletResponse객체에 contexttype, 응답코드, 응답메세지를 담아서 전송함

        nickname1 = request.getParameter("nick"); // html에서 사용한 input text(name=nick)를 여기에

        Connection connection = DriverManager.getConnection("jdbc:mysql://database-1.caxyf4kvshky.ap-northeast-2.rds.amazonaws.com:3306/capstone",
                "admin", "zoqtmxhs12");

        //System.out.println(nickname1);

        response.setContentType("text/html; charset=UTF-8"); // 문자셋 설정
        PrintWriter out = response.getWriter(); // js 텍스트 기록이 가능해짐



        try {

            PreparedStatement statement = connection.prepareStatement(query); // 미완성 문자전달
            statement.setString(1, nickname1); // 물음표 부분에

            resultSet = statement.executeQuery(); // 결과값 가져옴


            if(resultSet.next()){
                out.println("<script>alert('중복');history.go(-1);</script>");
                //history.go(-1)는 이전 페이지로 없으면 이동안함
            }
            else if(nickname1 == ""){
                out.println("<script>alert('닉네임을 입력하세요');history.go(-1);</script>");
            }
            else{
                statement = connection.prepareStatement(query1);
                statement.setString(1, nickname1);
                statement.executeUpdate(); // 데이터 db에 보내기
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return "room_list"; // 다음페이지에 올 뷰이름
    }


    //닉네임 삭제
    @PostMapping("/index/delete")
    public String logout(HttpServletRequest request) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mysql://database-1.caxyf4kvshky.ap-northeast-2.rds.amazonaws.com:3306/capstone",
                "admin", "zoqtmxhs12"); // sql에 연결

        try{
            PreparedStatement statement = connection.prepareStatement(query2);
            statement.setString(1, nickname1);
            statement.executeUpdate();

            request.getSession().invalidate(); // 현재 사용하는 세션 무효화
            request.getSession(true); // 새로운 세션ID를 발급해줌

            nickname1 = "";
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return "index";
    }
    @PostMapping("/index/red_mafia")
    public String room() {

        return "room_in";
    }


}
