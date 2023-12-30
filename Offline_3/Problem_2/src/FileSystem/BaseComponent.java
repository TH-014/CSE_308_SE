package FileSystem;

import java.util.HashMap;

public abstract class BaseComponent {
    private String name;
    private final FileType type;
    private final String creationTime;
    private BaseComponent parent;

    public BaseComponent(String name, FileType type, String creationTime, BaseComponent parent) {
        this.name = name;
        this.type = type;
        this.creationTime = creationTime;
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public FileType getType() {
        return type;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public BaseComponent getParent() {
        return parent;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParent(BaseComponent parent) {
        this.parent = parent;
    }

    public abstract int getSize();
    public abstract int getComponentCount();
    public String getDirectory()
    {
        if(parent == null)
            return " ";
        if(parent.getName().equals("root"))
            return getName()+":";
        else{
            return parent.getDirectory()+"\\"+getName();
        }
    }

    public void printDetails() {
        System.out.println("Name: " + name);
        System.out.println("Type: " + type);
        System.out.println("Size: " + getSize()+" kB");
        System.out.println("Directory: \"" + getDirectory()+"\"");
        System.out.println("Component count: " + getComponentCount());
        System.out.println("Creation time: " + creationTime);
    }

    public void showList() // for files; for containers, it will be overridden
    {
        System.out.println("Invalid command!");
    }

    public void addComponent(BaseComponent component) // for files; for containers, it will be overridden
    {
        System.out.println("Invalid command!");
    }
    public void removeComponent(BaseComponent component) // for files; for containers, it will be overridden
    {
        System.out.println("Invalid command!");
    }
    public BaseComponent getComponent(String name) // for files; for containers, it will be overridden
    {
        System.out.println("Invalid command!");
        return null;
    }
    public void showDetails(String name) // for files; for containers, it will be overridden
    {
        System.out.println("Invalid command!");
    }
}
