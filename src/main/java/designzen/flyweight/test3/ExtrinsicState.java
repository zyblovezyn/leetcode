package designzen.flyweight.test3;

public class ExtrinsicState {
    private String subject;
    private String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public int hashCode() {
        return subject.hashCode() + location.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ExtrinsicState) {
            ExtrinsicState state = (ExtrinsicState) obj;
            return state.getLocation().equalsIgnoreCase(location) && state.getSubject().equalsIgnoreCase(subject);
        }
        return false;
    }
}
