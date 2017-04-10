package org.lyk.interfaces;

public interface IBiTree<T extends Comparable<T>>
{
	//按数据大小有序添加（生成一个二叉排序树）
    public void addWithOrder(T[] data);
    //按先序从键盘接受数据
    public void addFirst(T[] dataArray,T endFlag);
    //按先序方式，将二叉树转换为数组
    public Object[] toArrayFirst();
    //按中序方式，将二叉树转换为数组
    public Object[] toArrayMiddle();
    //按后续方式，将二叉树转换为数组
    public Object[] toArrayLast();
    //二叉树大小
    public Integer size();
    /**
     * 判断二叉树是否为空
     * @return 如果二叉树为空，则返回true，否则返回false
     */
    public Boolean isEmpty();
    /**
     * 判断二叉树是否包含data元素
     * @param data 待查找的元素
     * @return 如果二叉树包含该元素，则返回T
     */
    public Boolean contains(T data);
    /**
     * 从二叉树中删除data元素
     * @param data
     */
    public void delete(T data);
    
    
    /**
     * 将二叉树中的某个元素进行替换
     * @param oldData 待替换的元素值
     * @param newData 新元素值
     */
    public void replace(T oldData, T newData);
}
