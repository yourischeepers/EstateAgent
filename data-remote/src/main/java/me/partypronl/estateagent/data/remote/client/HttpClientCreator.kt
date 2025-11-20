package me.partypronl.estateagent.data.remote.client

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.cookies.AcceptAllCookiesStorage
import io.ktor.client.plugins.cookies.HttpCookies
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import org.koin.core.annotation.Factory

@Factory
class HttpClientCreator {

    fun createHttpClient() = HttpClient(CIO) {
        install(Logging) {
            logger = Logger.ANDROID
            level = LogLevel.ALL
        }

        install(HttpCookies) {
            storage = AcceptAllCookiesStorage()
        }

        defaultRequest {
            header("User-Agent", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/142.0.0.0 Mobile Safari/537.36")
            header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8")
            header("Accept-Language", "en-GB,en-US;q=0.9,en;q=0.8")
            header("Referer", "https://www.funda.nl/")
            header("Upgrade-Insecure-Requests", "1")
            header("Sec-Fetch-Site", "same-origin")
            header("Sec-Fetch-Mode", "navigate")
            header("Sec-Fetch-User", "?1")
            header("Sec-Fetch-Dest", "document")
            header("Sec-CH-UA", "\"Chromium\";v=\"142\", \"Google Chrome\";v=\"142\", \"Not_A Brand\";v=\"99\"")
            header("Sec-CH-UA-Mobile", "?1")
            header("Sec-CH-UA-Platform", "\"Android\"")
            header("Cookie",
                $$"_hjSessionUser_2869769=eyJpZCI6ImVmMjA5YjM1LTkzMmYtNTlmOS04OTVjLWVlZjA1MzNkNGRhNCIsImNyZWF0ZWQiOjE3NTAwMjIxODM3NDIsImV4aXN0aW5nIjp0cnVlfQ==; didomi_consent=analytical-group,advertisement-group,functional-group,personalisation-group,cookies,select_basic_ads,create_ads_profile,select_personalized_ads,create_content_profile,select_personalized_content,measure_ad_performance,market_research,improve_products,use_limited_data_to_select_content,measure_content_performance,; _gcl_au=1.1.1446593789.1763561887; sessionstarted=true; SNLB=24204f7050db4318; .ASPXANONYMOUS=5-AgzQgcoIOrD2TIqOnlw3a4KpnKxLi4DkpuL2oLGnEVqSpgpRJLincOjOjzDh3-M5fjHL4u8Cpnga-3LaJFTfbC34a4jR67MwQKlKxSHXe9l2enuGBoNSflN9DxgfBLVnZwxlMKHy6jKF0cIvkzVX8TClQ1; _lr_env_src_ats=false; _ga=GA1.1.1410488101.1763561888; optimizelyEndUserId=oeu1763561887610r0.8267282833489141; pbjs-unifiedid=%7B%22TDID_LOOKUP%22%3A%22FALSE%22%2C%22TDID_CREATED_AT%22%3A%222025-11-19T14%3A18%3A07%22%7D; pbjs-unifiedid_cst=8SwpLFYsag%3D%3D; panoramaId_expiry=1764166687596; _cc_id=7d98fd1a27944e2a4bddd443af6b8e8d; panoramaId=dc5e5fee405b8a269a8302441130c8bd038a0e57780a6987d8ecc4cb23009dc4; ajs_anonymous_id=7680f676-3b2b-4750-afb2-5e2c61cf3847; wib=huur%3Dprovincie-noord-holland; optimizelySession=0; last_search=huur%3DAmsterdam%2520%252B%25203%2520filters%7CL3pvZWtlbi9odXVyP3NlbGVjdGVkX2FyZWE9JTVCJTIyYW1zdGVyZGFtJTIyJTVEJnByaWNlPSUyMjAtMTIwMCUyMiZvYmplY3RfdHlwZT0lNUIlMjJhcGFydG1lbnQlMjIlNUQmZmxvb3JfYXJlYT0lMjI0MC0lMjI%3D; _lr_retry_request=true; cto_bundle=Tk86ul9BVXhVN1ZNZTZZTVphZWdsb1RGaDRtZWFIaXN3VVlDSElIU05uZHh5SEFSMFd3aVFLN3ZkZGJ3WWZ0aVNvZ01tWWg3UG5IamQlMkZIVGJDTlZjM0xGMERyT3VSc0dhclltbkNxOGcwV3M4VU9wZHVoVXlVTTVvVnNTVmtVUTgzNmZKZkZjVGtZd2hGNDY3SUM1SlJuTXc1dUlDR1MxaER2cFEybXdsZXJkJTJGRWFoaCUyQiUyRnltUE5tZyUyRjdVVUR1b3JsWWc2; cto_bidid=_MIuFV9FdGlud29wY0I2cnhyMWxRJTJGQ3E0YmEwcUUlMkI3VDFPd2NwdElCJTJCYlFQSmZ6Y05oS0h3WDBLT0JxWFlkd3dyQTNHN283NDdRc3puZ2FiR2drVHl1OFR2c3p3VlhxYlYxeUl3eFZ2ZDZ6Zm5mUERuTEdnMDZlcGxLJTJCUW51NHp5MXV4NHVIRTZvTWpIdTZya2glMkJGT0pLUXdnJTNEJTNE; _ga_WLRNSHBY8J=GS2.1.s1763650880$o4$g0$t1763650880$j60$l0$h0; ak_bmsc=44C3021FAD4A34D204E51705B124DB48~000000000000000000000000000000~YAAQ2mMXAps6DYSaAQAAxsLIoR2nAmoTi7gccqunAu4LdLaqGO/BX56iEtlAi54lAyttkDevmYIzavhedx4szu1Ctw8De3gBvOHhJoCRIMDpuZtOqLbVa27ZH1cShRDk+BsC0h4zHTqf+KZ5/fFHK0WQOtM/hu/XgyChShtTx1P76wsEJ/MpD22+x/UirRT/XBtrbmFbYWbOa4CHw99QIlYA4hHpzMYX74jeabNe+IUTyDGzwWAFF73sSbuYPpSCq2sn+Lbx5oVqYn7SEeGou9lc4ektzp9Hqifz+VADDnBBwkTSnd8/o5cwfIAKr2kroU+yDTe3uEdY05BQz+fJUaKpJVOpnzn6NRpguE6yKELK6Gj9LWhtQTv7azWNdRFtXZOZZ1LvykEVHOsyhmdUDlXNbIbW3T+ZFrM05JxR/CvI8vOy/I2lDunZdehWKpRIXf3EoCFI/AKwsj3J; _hjSession_2869769=eyJpZCI6Ijg0N2Y4MTA5LWFmMDQtNDk2Ny04ZGY3LWRkZGI3NjhjZmE1NSIsImMiOjE3NjM2NTA4ODAxNTEsInMiOjAsInIiOjAsInNiIjowLCJzciI6MCwic2UiOjAsImZzIjowLCJzcCI6MH0=; g_state={\"i_l\":0,\"i_ll\":1763650880208,\"i_b\":\"OuRAS8EZWZ8wS1qlztzlFLV8C8+SZsM0SQytvkh0/FA\"}; __gads=ID=6b2dba8890022128:T=1750022204:RT=1763650880:S=ALNI_MbHBbroy4gaFKBQGprqKAXbqXAdQw; __gpi=UID=000010e02174d729:T=1750022204:RT=1763650880:S=ALNI_MbekfCFmE0o12LPeL4xOv7YYJr8TA; __eoi=ID=d104bf7c2e2bc87d:T=1750022204:RT=1763650880:S=AA-AfjaLIpz_RfRCjlDOoqiB_T_S; _dd_s=logs=1&id=76d75150-2c4f-4eb1-a1ae-4b3a6573ea39&created=1763650880077&expire=1763651780077"
            )
        }
    }
}
