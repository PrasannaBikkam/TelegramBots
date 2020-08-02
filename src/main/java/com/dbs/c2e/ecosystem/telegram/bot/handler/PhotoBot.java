package com.dbs.c2e.ecosystem.telegram.bot.handler;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class PhotoBot extends TelegramLongPollingBot {

	@Override
	public void onUpdateReceived(Update update) {

		// We check if the update has a message and the message has text
		if (update.hasMessage() && update.getMessage().hasText()) {
			// Set variables
			String message_text = update.getMessage().getText();
			long chat_id = update.getMessage().getChatId();
			if(message_text.equals("/start")) {
				SendMessage message = new SendMessage() // Create a message object object
						.setChatId(chat_id)
						.setText("We are ready to accept your command. Please select any of the following command /pic OR /help"); 
				try {
					execute(message); // Sending our message object to user
				} catch (TelegramApiException e) {
					e.printStackTrace();
				}
			}else if(message_text.equals("/pic")){
				// User sent /pic
				SendPhoto msg = new SendPhoto()
						.setChatId(chat_id)
						.setPhoto("AgACAgUAAxkBAAMbXyE7Ou7TkAvV2HLmSGjdtnssgbkAAuepMRtrTwhVkZIpqJNItSPd7cFqdAADAQADAgADeQADUhAGAAEaBA")
						.setCaption("Photo");
				try {
					execute(msg); // Call method to send the photo
				} catch (TelegramApiException e) {
					e.printStackTrace();
				}

			}
			else if (message_text.equals("/markup")) {
			    SendMessage message = new SendMessage() // Create a message object object
			                        .setChatId(chat_id)
			                        .setText("Here is your keyboard");
			    // Create ReplyKeyboardMarkup object
			    ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
			    // Create the keyboard (list of keyboard rows)
			    List<KeyboardRow> keyboard = new ArrayList<>();
			    // Create a keyboard row
			    KeyboardRow row = new KeyboardRow();
			    // Set each button, you can also use KeyboardButton objects if you need something else than text
			    row.add("Reject");
			    row.add("Accept");
			    // Add the first row to the keyboard
			    keyboard.add(row);
			    // Create another keyboard row
//			    row = new KeyboardRow();
//			    // Set each button for the second line
//			    row.add("Row 2 Button 1");
//			    row.add("Row 2 Button 2");
//			    row.add("Row 2 Button 3");
//			    // Add the second row to the keyboard
//			    keyboard.add(row);
			    // Set the keyboard to the markup
			    keyboardMarkup.setKeyboard(keyboard);
			    // Add it to the message
			    message.setReplyMarkup(keyboardMarkup);
			    try {
			        execute(message); // Sending our message object to user
			    } catch (TelegramApiException e) {
			        e.printStackTrace();
			    }
			}
			else if (message_text.equals("Accept")) {
			    SendMessage msg = new SendMessage()
			                .setChatId(chat_id)
			                .setText("Thanks for accepting");
			    
			    ReplyKeyboardRemove keyboardMarkup = new ReplyKeyboardRemove();
			    msg.setReplyMarkup(keyboardMarkup);
			    try {
			        execute(msg); // Call method to send the photo
			    } catch (TelegramApiException e) {
			        e.printStackTrace();
			    }
			}
			
			else {
				SendMessage message = new SendMessage() // Create a message object object
						.setChatId(chat_id)
						.setText("We are ready to accept your command. Please select any of the following command /pic OR /help"); 
				try {
					execute(message); // Sending our message object to user
				} catch (TelegramApiException e) {
					e.printStackTrace();
				}
			}
		}
		else if (update.hasMessage() && update.getMessage().hasPhoto()) {

			// Message contains photo
			// Set variables
			long chat_id = update.getMessage().getChatId();

			// Array with photo objects with different sizes
			// We will get the biggest photo from that array
			List<PhotoSize> photos = update.getMessage().getPhoto();
			// Know file_id
			String f_id = photos.stream()
					.sorted(Comparator.comparing(PhotoSize::getFileSize).reversed())
					.findFirst()
					.orElse(null).getFileId();
			// Know photo width
			int f_width = photos.stream()
					.sorted(Comparator.comparing(PhotoSize::getFileSize).reversed())
					.findFirst()
					.orElse(null).getWidth();
			// Know photo height
			int f_height = photos.stream()
					.sorted(Comparator.comparing(PhotoSize::getFileSize).reversed())
					.findFirst()
					.orElse(null).getHeight();
			// Set photo caption
			String caption = "file_id: " + f_id + "\nwidth: " + Integer.toString(f_width) + "\nheight: " + Integer.toString(f_height);
			SendPhoto msg = new SendPhoto()
					.setChatId(chat_id)
					.setPhoto(f_id)
					.setCaption(caption);
			try {
				execute(msg); // Call method to send the photo with caption
			} catch (TelegramApiException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public String getBotUsername() {
		return "gauravjavabot";
	}

	@Override
	public String getBotToken() {
		return "1287994026:AAETaF6BeGSM0Yc2ZTld5xvIOWdFxwhrktU";
	}
}
