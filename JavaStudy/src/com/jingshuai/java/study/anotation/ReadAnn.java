package com.jingshuai.java.study.anotation;

import java.lang.reflect.Field;

public class ReadAnn
{
    public static void main(String[] args)
    {
        // ��ȡ���ע��
        BaseLog obj = new BaseLog();
        // Annotation[] arr = obj.getClass().getAnnotations(); //�õ�����ע��
        MyTable table = obj.getClass().getAnnotation(MyTable.class); // ȡ��ָ��ע��

        System.out.println("��ע�ͣ�name��: " + table.name());
        System.out.println("��ע�ͣ�version��: " + table.version());

        // ��ȡ���Ե�ע��
        Field [] fields = obj.getClass().getDeclaredFields();
        for (Field f : fields)
        {
            // Annotation[] arr2 = f.getAnnotations(); //�õ�����ע��
            MyField ff = f.getAnnotation(MyField.class);// ȡ��ָ��ע��
            if(ff != null)
            {
                System.out.println("���ԣ�" + f.getName() + "��: " + ff.name() + " -- " + ff.type());
            }
        }
    }

}