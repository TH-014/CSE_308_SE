package FileSystem;

public class File extends BaseComponent{

    private int size;

    public File(String name, FileType type, int size, String creationTime, BaseComponent parent) {
        super(name, type, creationTime, parent);
        this.size = size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int getComponentCount() {
        return 0;
    }
}
