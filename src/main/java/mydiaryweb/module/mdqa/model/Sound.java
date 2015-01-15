package mydiaryweb.module.mdqa.model;


public class Sound {

    private int id;
    String sound;
    String voice;

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public String getVoice() {
        return voice;
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String stringify(){
        return getSound() + " " + getVoice();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sound sound1 = (Sound) o;

        if (id != sound1.id) return false;
        if (sound != null ? !sound.equals(sound1.sound) : sound1.sound != null) return false;
        if (voice != null ? !voice.equals(sound1.voice) : sound1.voice != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (sound != null ? sound.hashCode() : 0);
        result = 31 * result + (voice != null ? voice.hashCode() : 0);
        return result;
    }
}
