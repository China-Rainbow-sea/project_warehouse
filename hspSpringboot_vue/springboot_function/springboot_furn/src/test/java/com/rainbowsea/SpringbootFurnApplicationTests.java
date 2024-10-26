package com.rainbowsea;

import com.rainbowsea.bean.Furn;
import com.rainbowsea.mapper.FurnMapper;
import com.rainbowsea.serivce.FurnService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest  // Springboot 的测试，必须要有场景启动器，不然无法测试，无法运行
class SpringbootFurnApplicationTests {

	@Resource
	private FurnMapper furnMapper;


	@Resource
	private FurnService furnService;

	@Test
	void contextLoads() {
	}

	@Test
	public void testFurnMapper() {
		System.out.println("furnMapper--" + furnMapper.getClass());
		Furn furn = furnMapper.selectById(1);
		System.out.println("furn--" + furn);
	}


	@Test
	public void testFurnService() {
		List<Furn> furns = furnService.list();
		for ( Furn furn : furns) {
			System.out.println("furn--" + furn);
		}
	}

}
