package FileSystem;

import java.util.HashMap;

public class Container extends BaseComponent{

    private final HashMap<String, BaseComponent> childComponents;
    public Container(String name, FileType type, String creationTime, BaseComponent parent) {
        super(name, type, creationTime, parent);
        childComponents = new HashMap<>();
    }

    @Override
    public int getSize() {
        int size = 0;
        for(BaseComponent component : childComponents.values())
        {
            size += component.getSize();
        }
        return size;
    }

    @Override
    public int getComponentCount() {
        return childComponents.size();
    }

    @Override
    public BaseComponent getComponent(String name)
    {
        return childComponents.getOrDefault(name, null);
    }

    @Override
    public void showList()
    {
        for(BaseComponent component : childComponents.values())
        {
            System.out.println(component.getName()+"\t\t"+component.getSize()+" kB"+"\t\t"+component.getCreationTime());
        }
    }

    @Override
    public void addComponent(BaseComponent component)
    {
        if(!childComponents.containsKey(component.getName()))
        {
            childComponents.put(component.getName(), component);
        }
        else
        {
            System.out.println("A file or directory with the same name already exists!");
        }
    }

    @Override
    public void removeComponent(BaseComponent component) //incomplete
    {
        if(component.getType() == FileType.FILE)
        {
            childComponents.remove(component.getName());
        }
        else
        {
            if(component.getComponentCount() == 0)
            {
                childComponents.remove(component.getName());
            }
            else
            {
                Container container = (Container) component;
                for(BaseComponent childComponent : container.childComponents.values())
                {
                    removeComponent(childComponent);
                }
            }
        }
    }

    @Override
    public void showDetails(String name)
    {
        if(childComponents.containsKey(name))
        {
            childComponents.get(name).printDetails();
        }
        else
        {
            System.out.println("No such file or directory exists!");
        }
    }
}
