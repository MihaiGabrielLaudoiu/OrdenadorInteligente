package proyecto;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

/**
 * Clase para la manipulación de cuerdas vocales.
 *Como elemento adicional estoy utilizando esta libreria que me perrmite el TEXTTOSPEECH llamada freeTTS
 * @author Laudoiu Mihai Gabriel
 */
public class MisterRobot {
    /***
     * Metodo que permite reproducir un string como audio
     * @param oracion 
     */
    public static void hablar(String oracion) {

        try {
            System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
            Voice voice = VoiceManager.getInstance().getVoice("kevin16");
            if (voice != null) {
                voice.allocate();
                try {
                    voice.setRate(120);
                    voice.setPitch(100);
                    voice.setVolume(5);
                    voice.speak(oracion);
                } catch (Exception e1) {
                }

            } else {
                throw new IllegalStateException("Cannot find voice");
            }
        } catch (IllegalStateException e) {
            System.err.println(e.getMessage() );
        }
        

        
    }
}
