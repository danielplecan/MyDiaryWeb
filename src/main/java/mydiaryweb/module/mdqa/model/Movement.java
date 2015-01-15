package mydiaryweb.module.mdqa.model;


public class Movement {

    private int id;

    String movement;

    public String getMovement() {
        return movement;
    }

    public void setMovement(String movement) {
        this.movement = movement;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String stringify(){
        return getMovement();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movement movement1 = (Movement) o;

        if (id != movement1.id) return false;
        if (movement != null ? !movement.equals(movement1.movement) : movement1.movement != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (movement != null ? movement.hashCode() : 0);
        return result;
    }
}
