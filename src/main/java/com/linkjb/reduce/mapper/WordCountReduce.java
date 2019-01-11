package com.linkjb.reduce.mapper;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author sharkshen
 * @data 2019/1/11 11:08
 * @Useage
 *  * 第一个Text: 是传入的单词名称，是Mapper中传入的
 *  * 第二个：LongWritable 是该单词出现了多少次，这个是mapreduce计算出来的，比如 hello出现了11次
 *  * 第三个Text: 是输出单词的名称 ，这里是要输出到文本中的内容
 *  * 第四个LongWritable： 是输出时显示出现了多少次，这里也是要输出到文本中的内容
 */
public class WordCountReduce extends Reducer<Text, LongWritable,Text,LongWritable> {
    @Override
    protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
//        super.reduce(key, values, context);
        long count =0 ;
        for (LongWritable num:
             values) {
            count += num.get();

        }
        context.write(key,new LongWritable(count));
    }

    //    public static void main(String[] args) throws Exception {
//        Configuration conf = new Configuration();
//        Job job = Job.getInstance(conf, "word count");
//        job.setJarByClass(WordCount.class);
//        job.setMapperClass(TokenizerMapper.class);
//        job.setCombinerClass(IntSumReducer.class);
//        job.setReducerClass(IntSumReducer.class);
//        job.setOutputKeyClass(Text.class);
//        job.setOutputValueClass(IntWritable.class);
//        FileInputFormat.addInputPath(job, new Path(args[0]));
//        FileOutputFormat.setOutputPath(job, new Path(args[1]));
//        System.exit(job.waitForCompletion(true) ? 0 : 1);
//    }
}
