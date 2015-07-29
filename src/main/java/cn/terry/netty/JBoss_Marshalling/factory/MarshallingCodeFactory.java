package cn.terry.netty.JBoss_Marshalling.factory;

import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.Marshalling;
import org.jboss.marshalling.MarshallingConfiguration;

import io.netty.handler.codec.marshalling.DefaultMarshallerProvider;
import io.netty.handler.codec.marshalling.DefaultUnmarshallerProvider;
import io.netty.handler.codec.marshalling.MarshallerProvider;
import io.netty.handler.codec.marshalling.MarshallingDecoder;
import io.netty.handler.codec.marshalling.MarshallingEncoder;
import io.netty.handler.codec.marshalling.UnmarshallerProvider;


public class MarshallingCodeFactory {

	/**
	 * 创建解码器
	 * @return
	 */
	public static MarshallingDecoder buildMarshallingDecoder(){
		//1，通过Marshalling工具类拿到marshallingFactory实例；
		MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");  
		//2，创建MarshallingConfiguration对象
		final MarshallingConfiguration configuartion = new MarshallingConfiguration();
		//3,设定版本号为5
		configuartion.setVersion(5);
		//4，创建解码的provider，传入工厂实例和配置
		UnmarshallerProvider unmarshallerProvider = new DefaultUnmarshallerProvider(marshallerFactory, configuartion);
		//5，创建解码器，传入参数为provider和单个信息序列化后的最大长度
		MarshallingDecoder decoder = new MarshallingDecoder(unmarshallerProvider,1024);
		return decoder;
	}
	
	/**
	 * 创建编码器
	 * @return
	 */
	public static MarshallingEncoder buildMarshallingEncoder(){
		//1，通过Marshalling工具类拿到marshallingFactory实例；
		 MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");  
		//2，创建MarshallingConfiguration对象
		final MarshallingConfiguration configuartion = new MarshallingConfiguration();
		//3,设定版本号为5
		configuartion.setVersion(5);
		
		MarshallerProvider marshallerProvider = new DefaultMarshallerProvider(marshallerFactory, configuartion);
		//4，MarshallingEncoder用于将实现序列化接口的pojo对象序列化成二进制数组
		MarshallingEncoder encoder = new MarshallingEncoder(marshallerProvider);
		return encoder;
	}
}
