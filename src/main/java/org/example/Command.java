package org.example;

public class Command {
    private Action1 action;
    private String data;

    public Action1 getAction() {
        return action;
    }

    public String getData() {
        return data;
    }

    public Command(Action1 action) {
        this.action = action;
    }

    public Command(Action1 action, String data) {
        this.action = action;
        this.data = data;
    }
}
