package com.ld;

import com.ld.util.WordCountData;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.utils.MultipleParameterTool;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

public class WordCountJob {

    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        //make parameters available in the web interface;
        env.getConfig().setGlobalJobParameters(MultipleParameterTool.fromArgs(args));

        DataStream<String> txt = env.fromElements(WordCountData.WORDS);

        DataStream<Tuple2<String, Integer>> counts = 
                            txt.flatMap(new Tokenizer())
                            .keyBy(new KeySelector<Tuple2<String,Integer>,String>(){

                                private static final long serialVersionUID = 1L;

                                @Override
                                public String getKey(Tuple2<String, Integer> value) throws Exception {
                                    return value.f0;
                                }
                            }).sum(1);
        counts.print();
        env.execute("WordCount");
    }


    public static final class Tokenizer
                implements FlatMapFunction<String, Tuple2<String, Integer>> {

        private static final long serialVersionUID = 1L;

        @Override
        public void flatMap(String value, Collector<Tuple2<String, Integer>> out) throws Exception {
            String[] tokens = value.toLowerCase().split("\\W+");

            for (String string : tokens) {
                if(string.length() > 0){
                    out.collect(new Tuple2<>(string, 1));
                }
            }
        }
    }
    
}
