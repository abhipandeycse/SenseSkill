package com.senseskill.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.speech.v1.*;
import com.google.protobuf.ByteString;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.util.List;

@Service
public class SpeechToTextService {

    public String transcribe(byte[] audioBytes) throws Exception {
    	 try {
             // Load credentials from your JSON key file
             GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream("C:/Users/abhip/Documents/SenseSkill/backend/SenseSkill/src/main/resources/senseskill-7f936ff826dc.json"));
             SpeechSettings speechSettings = SpeechSettings.newBuilder()
                 .setCredentialsProvider(() -> credentials)
                 .build();

             // Create a SpeechClient using the settings
             try (SpeechClient speechClient = SpeechClient.create(speechSettings)) {
            ByteString audioData = ByteString.copyFrom(audioBytes);

            RecognitionAudio audio = RecognitionAudio.newBuilder()
                    .setContent(audioData)
                    .build();

            RecognitionConfig config = RecognitionConfig.newBuilder()
                    .setEncoding(RecognitionConfig.AudioEncoding.LINEAR16) // OR .MP3 based on frontend
                    .setSampleRateHertz(16000)
                    .setLanguageCode("en-US")
                    .build();

            RecognizeResponse response = speechClient.recognize(config, audio);
            List<SpeechRecognitionResult> results = response.getResultsList();

            StringBuilder transcript = new StringBuilder();
            for (SpeechRecognitionResult result : results) {
                transcript.append(result.getAlternativesList().get(0).getTranscript());
            }

            return transcript.toString();
        }
    }catch (Exception e) {
        e.printStackTrace();
        return "Error occurred during transcription.";
    }
    	 
    }
}
