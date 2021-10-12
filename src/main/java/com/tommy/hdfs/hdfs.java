package com.tommy.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URI;

/**
 * Hello world!
 */
public class hdfs {

    private Configuration conf;
    private FileSystem fs;

    public void conn() throws Exception {
        conf = new Configuration();
        fs = FileSystem.get(URI.create("hdfs://mycluster/"), conf, "liuhui");
    }

    public void mkdir() throws Exception {

        Path path = new Path("/hdfs_test");

        if (fs.exists(path)) {
            fs.delete(path, true);
        }
        fs.mkdirs(path);
    }

    public void upload() throws Exception {

        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(new File("./data/hello.txt")));

        Path path = new Path("/hdfs_test/hello.txt");
        FSDataOutputStream outputStream = fs.create(path);

        IOUtils.copyBytes(inputStream, outputStream, conf, true);
    }

    public void close() throws Exception {
        fs.close();
    }
}
