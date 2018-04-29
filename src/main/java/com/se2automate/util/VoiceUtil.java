package com.se2automate.util;

import javax.speech.AudioException;
import javax.speech.Central;
import javax.speech.EngineException;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import java.util.Locale;

/**
 * created By Gaurav Tiwari
 */
public class VoiceUtil {
    private static com.sun.speech.freetts.Voice systemVoice = null;
    private static Synthesizer synthesizer;

    public static void allocate() {
        try {
            // set property as Kevin Dictionary
            System.setProperty("freetts.voices",
                    "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");

            // Register Engine
            Central.registerEngineCentral
                    ("com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");

            // Create a Synthesizer
            synthesizer =
                    Central.createSynthesizer(new SynthesizerModeDesc(Locale.US));

            // Allocate synthesizer
            synthesizer.allocate();
            // Resume Synthesizer
            synthesizer.resume();
        } catch (EngineException e) {
            e.printStackTrace();
        } catch (AudioException ex) {
            ex.printStackTrace();
        }
    }

    public static void speak(String text) {
        synthesizer.speakPlainText(text, null);
        try {
            synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
            // speaks the given text until queue is empty.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void deallocate() {
        // Deallocate the Synthesizer.
        try {
            synthesizer.deallocate();
        } catch (EngineException e) {
            e.printStackTrace();
        }
    }
}
