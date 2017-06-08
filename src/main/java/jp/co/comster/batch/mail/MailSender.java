package jp.co.comster.batch.mail;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import jp.co.comster.batch.mail.dto.MailSendDto;

/**
 *
 * メール送信クラス<br>
 *
 * <pre>
 * 【修正履歴】
 * 日付       Ver. 担当者           修正内容
 * ---------------------------------------
 * 2017/06/06 1.0　COMSTER Yamaguchi  新規作成
 * </pre>
 *
 * @author COMSTER
 * @version 1.0
 *
 */
public class MailSender {

	/**
	 * メールを送信します。
	 *
	 * @param mailSendDto メールDTO
	 */
	public void sendMail(MailSendDto mailSendDto) {

		try {
			SimpleEmail email = new SimpleEmail();
			email.setHostName(mailSendDto.emailSetHostName);
			for (String mailaddress : mailSendDto.emailTo.split(","))
				if (mailaddress.indexOf("|") > -1)
					email.addTo(mailaddress.split("\\|")[0],mailaddress.split("\\|")[1]);
				else
					email.addTo(mailaddress);
			if (mailSendDto.emailFrom.indexOf("|") > -1)
				email.setFrom(mailSendDto.emailFrom.split("\\|")[0], mailSendDto.emailFrom.split("\\|")[1]);
			else
				email.setFrom(mailSendDto.emailFrom);
			if (mailSendDto.isEmailSetSSL) {
				email.setSSLOnConnect(true);
				email.setSslSmtpPort(mailSendDto.emailSSLPort);
				email.setAuthentication(mailSendDto.emailSSLUser, mailSendDto.emailSSLPass);
			}
			email.setSubject(mailSendDto.subject);
			email.setContent(mailSendDto.body, "text/plain; charset=ISO-2022-JP");
			email.setCharset("ISO-2022-JP");
			email.send();
		} catch (EmailException e) {
			throw new RuntimeException(e);
		}

	}

}
