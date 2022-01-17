package com.askc.insurance.integration;

import java.nio.charset.Charset;

import javax.servlet.ServletContext;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.askc.insurance.helper.PolicyTestHelper;
import com.askc.insurance.service.impl.PremiumCalculatorImpl;

/**
 * Integration test for {@link PremiumCalculatorImpl}
 * 
 * @author svkolev
 *
 */
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@RunWith(SpringRunner.class)
@ComponentScan(basePackages = "com.askc.insurance.*")
public class PremiumCalculatorITest {

	@Autowired
	private WebApplicationContext webApplicationContext;
	private MockMvc mockMvc;

	@Before
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();

		ServletContext servletContext = webApplicationContext.getServletContext();
		Assert.assertNotNull(servletContext);
		Assert.assertNotNull(webApplicationContext.getBean("policyController"));
	}

	/**
	 * Test case for verifying REST calls for specifically test case from
	 * implementation requirements
	 * 
	 * @throws Exception
	 *             any exception during execution
	 */
	@Test
	public void testCalculatePremiumPolicy1() throws Exception {
		MvcResult andReturn = mockMvc
				.perform(MockMvcRequestBuilders.get("/policy/calculate")
						.contentType(new MediaType(MediaType.APPLICATION_JSON.getType(),
								MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8")))
						.content(PolicyTestHelper.getContentsOfResource("/ITCase1.txt")))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

		Double result = Double.valueOf(andReturn.getResponse().getContentAsString());
		Assertions.assertEquals(2.28, result);
	}

	/**
	 * Test case for verifying REST calls for specifically test case from
	 * implementation requirements
	 * 
	 * @throws Exception
	 *             any exception during execution
	 */
	@Test
	public void testCalculatePremiumPolicy2() throws Exception {
		MvcResult andReturn = mockMvc
				.perform(MockMvcRequestBuilders.get("/policy/calculate")
						.contentType(new MediaType(MediaType.APPLICATION_JSON.getType(),
								MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8")))
						.content(PolicyTestHelper.getContentsOfResource("/ITCase2.txt")))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

		Double result = Double.valueOf(andReturn.getResponse().getContentAsString());
		Assertions.assertEquals(17.13, result);
	}

}
