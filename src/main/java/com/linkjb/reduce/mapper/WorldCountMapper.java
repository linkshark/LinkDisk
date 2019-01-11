package com.linkjb.reduce.mapper;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 *  * 这部分的输入是由mapreduce自动读取进来的
 *  * 简单的统计单词出现次数<br>
 *  * KEYIN 默认情况下，是mapreduce所读取到的一行文本的起始偏移量，Long类型，在hadoop中有其自己的序列化类LongWriteable
 *  * VALUEIN 默认情况下，是mapreduce所读取到的一行文本的内容，hadoop中的序列化类型为Text
 *  * KEYOUT 是用户自定义逻辑处理完成后输出的KEY，在此处是单词，String
 *  * VALUEOUT 是用户自定义逻辑输出的value，这里是单词出现的次数，Long
 * @author sharkshen
 * @data 2019/1/11 10:55
 * @Useage 统计单个字符出现的次数
 */
public class WorldCountMapper extends Mapper<LongWritable,Text,Text,LongWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //super.map(key, value, context);
        //这是mapreduce读取到的一行字符串
        String line = value.toString();
        String[] words = line.split(" ");
        for (String word:
             words) {
            //将单词输出为key,次数输出为value,这行数据被写入reduce
            context.write(new Text(word),new LongWritable(1));
        }
    }
}
