package academy.prog.enums;

public enum StatusType {
    active, inactive;

    public static DefaultPassword fromString(String input) {
        try {
            return DefaultPassword.valueOf(input);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
