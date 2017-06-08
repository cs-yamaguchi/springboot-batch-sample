package jp.co.comster.batch.mail.dto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * メール送信用DTOクラス<br>
 *
 * <pre>
 * 【修正履歴】
 * 日付       Ver. 担当者           修正内容
 * ---------------------------------------
 * 2016/01/28 1.0　COMSTER Yamaguchi  新規作成
 * </pre>
 *
 * @author COMSTER
 * @version 1.0
 */
@Component
public class MailSendDto {

	/**
	 * SSL
	 * @return
	 */
    @Value("${email.usessl}")
	public boolean isEmailSetSSL;

	/**
	 * SSLポート
	 * @return
	 */
    @Value("${email.sslport}")
	public String emailSSLPort;

	/**
	 * SSLユーザー
	 * @return
	 */
    @Value("${email.username}")
	public String emailSSLUser;

	/**
	 * SSLパス
	 * @return
	 */
    @Value("${email.password}")
	public String emailSSLPass;

	/**
	 * @return ホスト名
	 */
    @Value("${email.hostname}")
    public String emailSetHostName;

    /**
	 * @return Toアドレス
	 */
    @Value("${email.to}")
    public String emailTo;

	/**
	 * @return Fromアドレス
	 */
    @Value("${email.from}")
    public String emailFrom;

	/**
	 * @return タイトル
	 */
    public String subject;

	/**
	 * @return 本文
	 */
    public String body;

}
