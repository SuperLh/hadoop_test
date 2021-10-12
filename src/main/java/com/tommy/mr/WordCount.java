package com.tommy.mr;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import java.io.IOException;

public class WordCount {

    public static void main(String[] args) throws Exception {

        Configuration conf = new Configuration(true);
        conf.set("mapreduce.app-submission.cross-platform","true");

        Job job = Job.getInstance(conf);

        job.setJarByClass(WordCount.class);
        job.setJobName("WordCount");

        job.setJar("D:\\Code\\hadoop_test\\target\\hadoop_test-1.0-SNAPSHOT.jar");

        Path inputPath = new Path("/hdfs_test/hello.txt");
        TextInputFormat.addInputPath(job, inputPath);

        Path outputPath = new Path("/test_output");
        TextOutputFormat.setOutputPath(job, outputPath);

        job.setMapperClass(WordCountMapper.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setReducerClass(WordCountReducer.class);

        job.waitForCompletion(true);
    }
}
