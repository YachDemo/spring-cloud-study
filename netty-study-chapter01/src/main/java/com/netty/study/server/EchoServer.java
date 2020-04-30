package com.netty.study.server;

import com.netty.study.handler.EchoServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author YanCh
 * @version v1.0
 * Create by 2020-04-07 17:38
 **/
public class EchoServer {

    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) {

    }

    public void start() {
        // NioEventLoopGroup是处理I/O操作的多线程事件循环
        EventLoopGroup group = new NioEventLoopGroup();
        // ServerBootstrap是一个用于设置服务器的引导类。
        ServerBootstrap b = new ServerBootstrap();
        b.group(group)
                .channel(NioServerSocketChannel.class)// 使用NioServerSocketChannel类，用于实例化新的通道以接受传入连接
                .localAddress(new InetSocketAddress(port))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new EchoServerHandler());
                    }
                });
        try {
            ChannelFuture future = b.bind().sync();
            System.out.println(EchoServer.class.getName() +
                    " started and listening for connections on " + future.channel().localAddress());
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            try {
                group.shutdownGracefully().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
