package com.bit.myfood.controller.api;

import com.bit.myfood.controller.CrudController;
import com.bit.myfood.model.entity.Partner;
import com.bit.myfood.model.network.request.PartnerApiRequest;
import com.bit.myfood.model.network.response.PartnerApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/partner")
public class PartnerApiController extends CrudController<PartnerApiRequest,PartnerApiResponse,Partner> {
}
