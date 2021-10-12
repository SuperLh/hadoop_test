package com.tommy.mr;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class WordCountMapper extends Mapper<Object, Text, Text, IntWritable> {

    private IntWritable one = new IntWritable(1);

    private Text word = new Text();

    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {

        String[] values = value.toString().split(" ");

        for (String tmp : values) {
            word.set(tmp);
            context.write(word, one);
        }
    }
}
