interface Command {
    void execute();
}

class Light {
    public void turnOn() {
        System.out.println("-> The Light is on");
    }

    public void turnOff() {
        System.out.println("-> The Light is off");
    }
}

class LightOnCommand implements Command {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn();
    }
}

class LightOffCommand implements Command {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOff();
    }
}

class RemoteControl {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        if (command != null) {
            command.execute();
        } else {
            System.out.println("Something happened!");
        }
    }
}

public class CommandPatternExample {
    public static void main(String[] args) {
        Light myLight = new Light();
        Command turnOn = new LightOnCommand(myLight);
        Command turnOff = new LightOffCommand(myLight);

        RemoteControl remote = new RemoteControl();

        remote.setCommand(turnOn);
        remote.pressButton();

        remote.setCommand(turnOff);
        remote.pressButton();
    }
}