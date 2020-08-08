package com.dbs.c2e.ecosystem.telegram.bot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.dbs.c2e.ecosystem.telegram.bot.handler.GauravJavaBot;
import com.dbs.c2e.ecosystem.telegram.bot.handler.InlineProcessingBot;

@SpringBootApplication
public class BotApplication {

	public static void main(String[] args) {
		SpringApplication.run(BotApplication.class, args);
		
		ApiContextInitializer.init();
		
		TelegramBotsApi tBot=new TelegramBotsApi();
		
		try {
			tBot.registerBot(new InlineProcessingBot());
//			tBot.registerBot(new GauravJavaBot());
		}catch(TelegramApiException tae) {
			tae.printStackTrace();
		}
		
	}

}
