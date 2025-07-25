package com.senseskill.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.texttospeech.v1.*;
import com.google.protobuf.ByteString;

import java.io.FileInputStream;

import org.springframework.stereotype.Service;

@Service
public class TextToSpeechService {

    public byte[] synthesizeSpeech(String text) throws Exception {
    	
    		GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream("C:/Users/abhip/Documents/SenseSkill/backend/SenseSkill/src/main/resources/senseskill-7f936ff826dc.json"));
    		 TextToSpeechSettings settings = TextToSpeechSettings.newBuilder()
    	                .setCredentialsProvider(() -> credentials)
    	                .build();
    		 
        try (TextToSpeechClient textToSpeechClient = TextToSpeechClient.create(settings)) {

            SynthesisInput input = SynthesisInput.newBuilder().setText(text).build();

            VoiceSelectionParams voice = VoiceSelectionParams.newBuilder()
                    .setLanguageCode("en-US")
                    .setSsmlGender(SsmlVoiceGender.MALE)
                    .build();

            AudioConfig audioConfig = AudioConfig.newBuilder()
                    .setAudioEncoding(AudioEncoding.MP3)
                    .build();

            SynthesizeSpeechResponse response = textToSpeechClient.synthesizeSpeech(input, voice, audioConfig);
            ByteString audioContents = response.getAudioContent();

            return audioContents.toByteArray();
        }
    }
    }

