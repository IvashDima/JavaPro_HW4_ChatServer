package academy.prog.enums;

public enum DefaultPassword {
    aaa;

    public static DefaultPassword fromString(String input) {
        try {
            return DefaultPassword.valueOf(input);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}