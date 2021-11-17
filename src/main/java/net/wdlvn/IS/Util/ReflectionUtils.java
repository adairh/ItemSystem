package net.wdlvn.IS.Util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ReflectionUtils
{
  public static Object cast(Class<?> clazz, Object castObj)
  {
    return clazz.cast(castObj);
  }
  
  public static Object getEnum(String name, Class<?> clazz)
  {
    return getStaticField(name, clazz);
  }
  
  public static Object getField(String field, Class<?> clazz, Object obj)
  {
    try
    {
      Field f = clazz.getDeclaredField(field);
      f.setAccessible(true);
      return f.get(obj);
    }
    catch (NoSuchFieldException|IllegalAccessException e)
    {
      e.printStackTrace();
    }
    return null;
  }
  
  public static Object getStaticField(String field, Class<?> clazz)
  {
    try
    {
      Field f = clazz.getDeclaredField(field);
      f.setAccessible(true);
      return f.get(null);
    }
    catch (NoSuchFieldException|IllegalAccessException e)
    {
      e.printStackTrace();
    }
    return null;
  }
  
  public static void setField(String field, Class<?> clazz, Object obj, Object value)
  {
    try
    {
      Field f = clazz.getDeclaredField(field);
      f.setAccessible(true);
      f.set(obj, value);
    }
    catch (NoSuchFieldException|IllegalAccessException e)
    {
      e.printStackTrace();
    }
  }

  public static boolean isContainField(String field, Class<?> clazz)
  {
    Field[] fs = clazz.getDeclaredFields();
    List<String> fieldList = new ArrayList<String>();
    for (int i = 0;i<fs.length;i++){
      fieldList.add(fs[i].getName().toLowerCase());
    }
    if (fieldList.contains(field.toLowerCase())){
      return true;
    }
    return false;
  }

  public static void setStaticField(String field, Class<?> clazz, Object value)
  {
    try
    {
      Field f = clazz.getDeclaredField(field);
      f.setAccessible(true);
      f.set(null, value);
    }
    catch (NoSuchFieldException|IllegalAccessException e)
    {
      e.printStackTrace();
    }
  }
  

  
  public static Object getStaticMethod(String method, Class<?> clazz)
  {
    try
    {
      Method f = clazz.getDeclaredMethod(method, new Class[0]);
      f.setAccessible(true);
      return f.invoke(null, new Object[0]);
    }
    catch (IllegalAccessException|NoSuchMethodException|InvocationTargetException e)
    {
      e.printStackTrace();
    }
    return null;
  }
  
  public static Object getMethod(String method, Class<?> clazz, Object obj)
  {
    try
    {
      Method f = clazz.getDeclaredMethod(method, new Class[0]);
      f.setAccessible(true);
      return f.invoke(obj, new Object[0]);
    }
    catch (IllegalAccessException|NoSuchMethodException|InvocationTargetException e)
    {
      e.printStackTrace();
    }
    return null;
  }
  

  public static Object getConstructor(Class<?> clazz)
  {
    try
    {
      Constructor<?> f = clazz.getDeclaredConstructor(new Class[0]);
      f.setAccessible(true);
      return f.newInstance(new Object[0]);
    }
    catch (IllegalAccessException|NoSuchMethodException|InvocationTargetException|InstantiationException e)
    {
      e.printStackTrace();
    }
    return null;
  }
}
