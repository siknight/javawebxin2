package com.test;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class First {

    /**
     * 创建索引
     * @throws Exception
     */
    @Test
    public void createIndex() throws Exception {

        //指定索引库存放的路径
        FSDirectory open = FSDirectory.open(new File("F:\\lianxi\\temp").toPath());

        //创建indexwriterCofig对象
        IndexWriterConfig config = new IndexWriterConfig();
        //创建indexwriter对象
        IndexWriter indexWriter = new IndexWriter(open, config);

        File file = new File("F:\\lianxi\\searchsource");

        for (File f:file.listFiles()){
            //文件名
            String filename = f.getName();
            //文件内容
            String filecontent = FileUtils.readFileToString(f,"utf-8");

            //文件路径
            String filepath = f.getPath();

            //文件的大小
            long filesize = FileUtils.sizeOf(f);


            //创建文件名域
            //第一个参数：域的名称
            //第二个参数：域的内容
            //第三个参数：是否存储
            TextField filenameField = new TextField("filename", filename, Field.Store.YES);
            //文件内容域
            Field fileContentField = new TextField("content", filecontent, Field.Store.YES);
            //文件路径域（不分析、不索引、只存储）
            Field filePathField = new TextField("path", filepath, Field.Store.YES);
            //文件大小域
            Field fileSizeField = new TextField("size", filesize + "", Field.Store.YES);



//            Field filenameField = new TextField("name", filename, Field.Store.YES);
//            //Field fieldPath = new TextField("path", filePath, Field.Store.YES);
//            Field filePathField = new StoredField("path", filepath);
//            Field fileContentField = new TextField("content", filecontent, Field.Store.YES);
//            //Field fieldSize = new TextField("size", fileSize + "", Field.Store.YES);
//            Field fileSizeField = new LongPoint("size", filesize);
//            Field fieldSizeStore = new StoredField("size", filesize);

            Document document = new Document();

            document.add(filenameField);
            document.add(fileContentField);
            document.add(filePathField);
            document.add(fileSizeField);

            indexWriter.addDocument(document);

        }

        //关闭indexwriter
        indexWriter.close();
    }

    /**
     * 创建索引
     * @throws Exception
     */
    @Test
    public void createIndex2() throws Exception {
        //1、创建一个Director对象，指定索引库保存的位置。
        //把索引库保存在内存中
        //Directory directory = new RAMDirectory();
        //把索引库保存在磁盘
        Directory directory = FSDirectory.open(new File("F:\\lianxi\\temp").toPath());
        //2、基于Directory对象创建一个IndexWriter对象
        IndexWriterConfig config = new IndexWriterConfig(new IKAnalyzer());
        IndexWriter indexWriter = new IndexWriter(directory, config);
        //3、读取磁盘上的文件，对应每个文件创建一个文档对象。
        File dir = new File("F:\\lianxi\\searchsource");
        File[] files = dir.listFiles();
        for (File f :
                files) {
            //取文件名
            String fileName = f.getName();
            //文件的路径
            String filePath = f.getPath();
            //文件的内容
            String fileContent = FileUtils.readFileToString(f, "utf-8");
            //文件的大小
            long fileSize = FileUtils.sizeOf(f);
            //创建Field
            //参数1：域的名称，参数2：域的内容，参数3：是否存储
            Field fieldName = new TextField("name", fileName, Field.Store.YES);
            //Field fieldPath = new TextField("path", filePath, Field.Store.YES);
            Field fieldPath = new StoredField("path", filePath);
            Field fieldContent = new TextField("content", fileContent, Field.Store.YES);
            //Field fieldSize = new TextField("size", fileSize + "", Field.Store.YES);
            Field fieldSizeValue = new LongPoint("size", fileSize);
            Field fieldSizeStore = new StoredField("size", fileSize);
            //创建文档对象
            Document document = new Document();
            //向文档对象中添加域
            document.add(fieldName);
            document.add(fieldPath);
            document.add(fieldContent);
            //document.add(fieldSize);
            document.add(fieldSizeValue);
            document.add(fieldSizeStore);
            //5、把文档对象写入索引库
            indexWriter.addDocument(document);
        }
        //6、关闭indexwriter对象
        indexWriter.close();
    }

    /**
     * 查询索引
     * @throws Exception
     */
    @Test
    public void selectindex() throws IOException {
        //指定索引库存放的路径
        //D:\temp\index
        Directory directory = FSDirectory.open(new File("F:\\lianxi\\temp").toPath());
        //创建indexReader对象
        IndexReader indexReader = DirectoryReader.open(directory);
        //创建indexsearcher对象
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);

        //创建查询
        Query query = new TermQuery(new Term("filename", "apache"));

        //第一个参数是查询对象，第二个参数是查询结果返回的最大值
        TopDocs topDocs = indexSearcher.search(query, 10);

        //输出总条数
        System.out.println(topDocs.totalHits);

        //topDocs.scoreDocs存储了document对象的id
        for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
            //scoreDoc.doc属性就是document对象的id
            //根据document的id找到document对象
            Document document = indexSearcher.doc(scoreDoc.doc);
            System.out.println(document.get("filename"));
//            System.out.println(document.get("content"));
            System.out.println(document.get("path"));
            System.out.println(document.get("size"));
            System.out.println("-------------------------");
        }

        indexReader.close();
    }

    /**
     * 分析器的分词效果
     * @throws IOException
     */
    @Test
    public void test03() throws IOException {
        //1）创建一个Analyzer对象，StandardAnalyzer对象
//        Analyzer analyzer = new StandardAnalyzer();
        Analyzer analyzer = new IKAnalyzer();
        //2）使用分析器对象的tokenStream方法获得一个TokenStream对象
        TokenStream tokenStream = analyzer.tokenStream("", "2017年12月14日 - 传智播客Lucene概述公安局Lucene是一款高性能的、可扩展的信息检索(IR)工具库。信息检索是指文档搜索、文档内信息搜索或者文档相关的元数据搜索等操作。");
        //3）向TokenStream对象中设置一个引用，相当于数一个指针
        CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
        //4）调用TokenStream对象的rest方法。如果不调用抛异常
        tokenStream.reset();
        //5）使用while循环遍历TokenStream对象
        while(tokenStream.incrementToken()) {
            System.out.println(charTermAttribute.toString());
        }
        //6）关闭TokenStream对象
        tokenStream.close();
    }





}
