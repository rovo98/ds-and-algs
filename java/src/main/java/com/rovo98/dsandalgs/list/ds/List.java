package com.rovo98.dsandalgs.list.ds;

/**
 * ADT 线性表 (list)
 * Data
 *     线性表的数据对象集合为{a1, a2, ..., an}, 每个元素的类型均为DataType。其中，除第
 * 一个元素a1外，每个元素都有且只有一个直接前驱元素，除了最后一个元素an外，每个元素有且只有
 * 一个直接后继元素。数据元素之间的关系是一对一关系。
 *
 * Operation
 *      isEmpty() :         若线性表为空，返回true,否则返回false.
 *      clearList() :       将线性表清空.
 *      getElem(i) :        获取线性表中第i个位置上的元素.
 *      locateElem(e) :     在线性表中查找与给定值e相等的元素，查找成功返回序号，失败返回0.
 *      insertElem(i, e) :  在线性表中第i个位置插入新元素e.
 *      deleteElem(i) :     删除线性表中第i个位置元素，并返回元素值.
 *      getLength() :       获取当前线性表的的元素个数.
 * endADT
 *
 * @author rovo98
 * Date: 3/2/2018
 */
public interface List<Item> extends Iterable<Item> {
    boolean isEmpty();
    void clearList();
    Item getElem(int index);
    int locateElem(Item e);
    void insertElem(int index, Item e);
    Item deleteElem(int index);
    int size();
}
