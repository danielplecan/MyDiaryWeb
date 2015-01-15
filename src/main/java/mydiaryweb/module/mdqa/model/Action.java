package mydiaryweb.module.mdqa.model;

import mydiaryweb.module.mdqa.util.RandomUtil;

public class Action {
    private Integer id;
    private ActionDate actionDate;
    private Face face;
    private Location location;
    private Movement movement;
    private Sound sound;

    public Action() {
        this.setId(Integer.valueOf(RandomUtil.basicRandId()));
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Face getFace() {
        return face;
    }

    public void setFace(Face face) {
        this.face = face;
    }

    public int getId() {
        return id;
    }

    public Movement getMovement() {
        return movement;
    }

    public void setMovement(Movement movement) {
        this.movement = movement;
    }

    public Sound getSound() {
        return sound;
    }

    public void setSound(Sound sound) {
        this.sound = sound;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ActionDate getActionDate() {
        return actionDate;
    }

    public void setActionDate(ActionDate actionDate) {
        this.actionDate = actionDate;
    }

    public String stringify() {
        StringBuilder builder = new StringBuilder();

        if (actionDate != null) {
            builder.append(actionDate.stringify());
            builder.append(" ");
        }

        if (face != null) {
            builder.append(face.stringify());
            builder.append(" ");
        }
        if (movement != null) {
            builder.append(movement.stringify());
            builder.append(" ");
        }
        if (location != null) {
            builder.append(location.stringify());
            builder.append(" ");
        }
        if (sound != null) {
            builder.append(sound.stringify());
            builder.append(" ");
        }

        return builder.toString();

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Action action = (Action) o;

        if (actionDate != null ? !actionDate.equals(action.actionDate) : action.actionDate != null) return false;
        if (face != null ? !face.equals(action.face) : action.face != null) return false;
        if (id != null ? !id.equals(action.id) : action.id != null) return false;
        if (location != null ? !location.equals(action.location) : action.location != null) return false;
        if (movement != null ? !movement.equals(action.movement) : action.movement != null) return false;
        if (sound != null ? !sound.equals(action.sound) : action.sound != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (actionDate != null ? actionDate.hashCode() : 0);
        result = 31 * result + (face != null ? face.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (movement != null ? movement.hashCode() : 0);
        result = 31 * result + (sound != null ? sound.hashCode() : 0);
        return result;
    }
}
