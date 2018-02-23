package com.epam.khrypushyna.shop.creator.reflection;

import com.epam.khrypushyna.shop.creator.Creator;
import com.epam.khrypushyna.shop.creator.mode.Mode;
import com.epam.khrypushyna.shop.entity.Furniture;
import java.lang.reflect.Method;

public class AddFurnitureReflection extends Creator {

    private Mode mode;
    private Class<? extends Furniture> furnitureClass;

    public AddFurnitureReflection(Mode mode, Class<? extends Furniture> furnitureClass) {
        this.mode = mode;
        this.furnitureClass = furnitureClass;
    }

    @Override
    protected Furniture getInstance() {
        Furniture item = null;
        try {
            item = furnitureClass.newInstance();
        } catch (Exception e) {
            System.err.println("Exception during new instance creation");
        }
        return item;
    }

    @Override
    protected void fill(Furniture item) {
        try {
            Method[] methods = furnitureClass.getMethods();
            for (Method method : methods) {
                String methodName = method.getName();
                if (method.isAnnotationPresent(FieldMark.class)) {
                    Class[] parameters = method.getParameterTypes();
                    for (Class c : parameters) {
                        if (c == boolean.class) {
                            method.invoke(item, mode.getBoolean());
                        }
                        else {
                            String modeMethodName = "get" + methodName.substring(3);
                            method.invoke(item, Mode.class.getMethod(modeMethodName).invoke(mode));
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Exception during filling the Furniture using reflection");
        }
    }

}
