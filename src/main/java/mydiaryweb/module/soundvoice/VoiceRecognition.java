package mydiaryweb.module.soundvoice;

import matlabcontrol.MatlabConnectionException;
import matlabcontrol.MatlabInvocationException;
import matlabcontrol.MatlabProxy;
import matlabcontrol.MatlabProxyFactory;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel
 */
public class VoiceRecognition {
    public static String recognizeVoice(String soundPath) {
        try {
            MatlabProxyFactory factory = new MatlabProxyFactory();
            MatlabProxy proxy = factory.getProxy();
            String f = "voicerecognition('" + soundPath + "')";
            Object[] d = proxy.returningEval(f, 1);
            String arg = (String) d[0];
            proxy.exit();
            return arg;

        } catch (MatlabInvocationException | MatlabConnectionException ex) {
            Logger.getLogger(VoiceRecognition.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
}
