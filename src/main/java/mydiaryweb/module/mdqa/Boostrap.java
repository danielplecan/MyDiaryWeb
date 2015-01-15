package mydiaryweb.module.mdqa;

import mydiaryweb.entity.inferences.Main;
import org.joda.time.DateTimeZone;

import java.util.List;

public class Boostrap {
    Start start;

    private static Boostrap instance = null;

    protected Boostrap() {
        this.boot();
        start = new Start();
    }

    public static Boostrap inst() {
        if (instance == null) {
            instance = new Boostrap();
        }
        return instance;
    }

    private void boot() {
        //!!IMPORTANT set default joda timezone
        //Stanford SUTime library uses this default!
        //We do all anwering logic using UTC
        DateTimeZone.setDefault(DateTimeZone.forID("UTC"));

    }

    public String processResponse(String userInput) {
        return this.start.processResponse(userInput);
    }

    public void setData(List<Main> data) {
        this.start.setData(data);
    }
}
