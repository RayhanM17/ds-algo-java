package com.rayhanm17;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        LinkedList myLL = new LinkedList(1);

        myLL.append(2);
        myLL.append(3);
        myLL.append(4);
        myLL.append(5);
        myLL.append(6);

        myLL.printList();

        System.out.println("Reversed List");

        myLL.reverse();
        
        myLL.printList();
    }
}
