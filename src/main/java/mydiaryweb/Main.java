package mydiaryweb;

import matlabcontrol.MatlabConnectionException;
import matlabcontrol.MatlabInvocationException;
import matlabcontrol.MatlabProxy;
import matlabcontrol.MatlabProxyFactory;


/**
 * Created by Calin on 16-Jan-15.
 */
public class Main {

    public static void main(String[] args) throws MatlabInvocationException {

        try {
            MatlabProxyFactory factory = new MatlabProxyFactory();
            MatlabProxy proxy = factory.getProxy();
            String f = "voicerecognition('D:/MATLAB/toolbox/optim/optim/test/s6.wav')";
            Object[] d = proxy.returningEval(f,1);
            String arg = (String) d[0];
            System.out.println(arg);

            proxy.exit();


        } catch (MatlabConnectionException e) {
            e.printStackTrace();
        }

    }

}
