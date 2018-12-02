package app;

public class Invite {
    private int user1;
    private int user2;
    public Invite(int user1, int user2){
        this.user1 = user1;
        this.user2 = user2;
    }

    public int getUser1() {
        return user1;
    }

    public void setUser1(int user1) {
        this.user1 = user1;
    }

    public int getUser2() {
        return user2;
    }

    public void setUser2(int user2) {
        this.user2 = user2;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + this.user1;
        hash = 43 * hash + this.user2;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Invite other = (Invite) obj;
        return true;
    }
    
}
