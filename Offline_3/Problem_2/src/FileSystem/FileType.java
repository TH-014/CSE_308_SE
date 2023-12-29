package FileSystem;

public enum FileType {
    FILE,
    DIRECTORY,
    ROOT,
    DRIVE;

    public String toString() {
        return switch (this) {
            case FILE -> "File";
            case DIRECTORY -> "Folder";
            case ROOT -> "Root";
            case DRIVE -> "Drive";
            default -> throw new IllegalArgumentException();
        };
    }
}
