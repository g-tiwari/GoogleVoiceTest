package com.se2automate.util;


import in.co.gauravtiwari.voice.client.VoiceAutomationClient;
import in.co.gauravtiwari.voice.clientresources.ClientOperationException;
import in.co.gauravtiwari.voice.clientresources.Voice;
import in.co.gauravtiwari.voice.design.Language;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.URL;

/**
 * created By Gaurav Tiwari
 */
public class VoiceUtil {
    static final Logger LOG = LoggerFactory.getLogger(VoiceUtil.class);

    public static void speak(String text, Language language){

        try {
            Voice voice = new Voice(text, language);
            VoiceAutomationClient voiceAutomationClient = new VoiceAutomationClient();
            voiceAutomationClient.load(voice);
            voiceAutomationClient.play(voice);
            LOG.info(voice.getFilename());
            LOG.info(voice.getText());
            LOG.info(voice.getVoiceName());
            LOG.info(voice.getVoiceLanguage().toString());
        }catch (ClientOperationException e){
            e.printStackTrace();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public static void playInternetVoiceFile(String url){

        try {
            URL voiceUrl = new URL(url);
            Voice voice = new Voice(voiceUrl);
            VoiceAutomationClient voiceAutomationClient = new VoiceAutomationClient();
            voiceAutomationClient.load(voice);
            voiceAutomationClient.play(voice);
            LOG.info(voice.getFilename());
        }catch (ClientOperationException e){
            e.printStackTrace();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
