package jp.co.comster.batch;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jp.co.comster.batch.mail.MailSender;
import jp.co.comster.batch.mail.dto.MailSendDto;
import jp.co.comster.batch.model.Staff;
import jp.co.comster.batch.service.SampleService;

@SpringBootApplication
public class BatchSampleApplication {


	private static final Logger logger = LoggerFactory.getLogger("APP_LOG");

    /**
     * エントリポイント
     * @param args
     */
	public static void main(String[] args) {

		try (ConfigurableApplicationContext context = SpringApplication.run(BatchSampleApplication.class, args)) {
			context.getBean(BatchSampleApplication.class).doMain(context);
		}

	}

	private void doMain(ConfigurableApplicationContext context) {
		logger.info("Start!");
		SampleService sample = context.getBean(SampleService.class);
		System.out.println("-----------------");
		sample.getAllStaff().forEach(s -> { System.out.println(s.name); });

		System.out.println("-----------------");
		sample.getAllStaff().forEach(System.out::println);

		System.out.println("-----------------");
		Staff staff = sample.getStaff(1);
		System.out.println(staff);

		System.out.println("-----------------");
		int maxStaffId = sample.getMaxStaffId();
//		System.out.println(maxStaffId);
//		maxStaffId++;
//		staff.id = maxStaffId;
//		staff.name = staff.name + maxStaffId;
//		sample.save(staff);
//
//		maxStaffId = sample.getMaxStaffId();
//		System.out.println(maxStaffId);
//		maxStaffId++;
//		staff.id = maxStaffId;
//		staff.name = staff.name + maxStaffId;
//		sample.saveAndThrowRuntimeException(staff);
//
//		maxStaffId = sample.getMaxStaffId();
//		System.out.println(maxStaffId);
//		maxStaffId++;
//		staff.id = maxStaffId;
//		staff.name = staff.name + maxStaffId;
//		sample.saveAndThrowRuntimeExceptionWithTransactional(staff);

//		maxStaffId = sample.getMaxStaffId();
		System.out.println(maxStaffId);
		maxStaffId++;
		staff.id = maxStaffId;
		staff.name = staff.name + maxStaffId;
		try {
			sample.saveAndThrowExceptionWithTransactional(staff);
		} catch (Exception e) {
			logger.error("hoge", e);
		}

		sendMail(context.getBean(MailSendDto.class));

		System.out.println("-----------------");
		logger.info("End!");

	}

    @Value("${email.freemarker.template-path}")
    private String freemarkerEmailTemplatePath;

    @Value("${email.freemarker.template-file}")
    private String freemarkerEmailTemplateFile;

    @Value("${email.subject}")
    private String emailSubject;

    public void sendMail(MailSendDto mailSendDto) {
		StringWriter writer = new StringWriter();
		Configuration freeMarkerCfg = new Configuration(Configuration.getVersion());
		try {
			freeMarkerCfg.setDirectoryForTemplateLoading(new File(System.getProperty("java.class.path").split(";")[0] + freemarkerEmailTemplatePath));
			Template t = freeMarkerCfg.getTemplate(freemarkerEmailTemplateFile, "UTF-8");
			try {
				t.process(null, writer);
			} catch (TemplateException e) {
				logger.error("hoge", e);
			}
		} catch (IOException e) {
			logger.error("hoge", e);
		}

		mailSendDto.subject = emailSubject;
		mailSendDto.body = writer.toString();
		new MailSender().sendMail(mailSendDto);

    }
}
