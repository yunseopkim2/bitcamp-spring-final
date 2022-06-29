package kr.co.clozet.users.services;

import kr.co.clozet.users.domains.UserDTO;
import org.apache.commons.mail.HtmlEmail;

/**
 * packageName    : kr.co.clozet.users.services
 * fileName       : MailUtil
 * author         : kimyunseop
 * date           : 2022-06-28
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-28        kimyunseop       최초 생성
 */
public class MailUtil {
    public void sendMail(UserDTO user) throws Exception{
        // Mail Server 설정
        String charSet = "utf-8";
        String hostSMTP = "smtp.gmail.com"; //네이버 이용시 smtp.naver.com
        String hostSMTPid = "dbstjqdlwksj@gmail.com";
        String hostSMTPpwd = "yoseph12!@";

        // 보내는 사람 EMail, 제목, 내용
        String fromEmail = "dbstjqdlwksj@gmail.com";//"보내는 사람 이메일주소(받는 사람 이메일에 표시됨)";
        String fromName = "clozet";//"프로젝트이름 또는 보내는 사람 이름";
        String subject = "임시비밀번호 발금";
        String msg = "임시비밀번호";

            subject = "베프마켓 임시 비밀번호 입니다.";
            msg += "<div align='center' style='border:1px solid black; font-family:verdana'>";
            msg += "<h3 style='color: blue;'>";
            msg += user.getUsername() + "님의 임시 비밀번호 입니다. 비밀번호를 변경하여 사용하세요.</h3>";
            msg += "<p>임시 비밀번호 : ";
            msg += user.getPassword() + "</p></div>";


        // 받는 사람 E-Mail 주소
        String mail = user.getEmail();
        try {
            HtmlEmail email = new HtmlEmail();
            email.setDebug(true);
            email.setCharset(charSet);
            email.setSSLOnConnect(true);
            email.setHostName(hostSMTP);
            email.setSmtpPort(465); //네이버 이용시 587

            email.setAuthentication(hostSMTPid, hostSMTPpwd);
            email.setStartTLSEnabled(true);
            email.addTo(mail, charSet);
            email.setFrom(fromEmail, fromName, charSet);
            email.setSubject(subject);
            email.setHtmlMsg(msg);
            email.send();
        } catch (Exception e) {
            System.out.println("메일발송 실패 : " + e);
        }
    }
}
