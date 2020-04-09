package com.example.spos_sdk2;

public class EnvBase {

    public enum PayGate {

        PAYDOLLAR {
            @Override
            public String toString() {
                return "paydollar";
            }
        }, SIAMPAY {
            @Override
            public String toString() {
                return "siampay";
            }
        }, PESOPAY {
            @Override
            public String toString() {
                return "pesopay";
            }
        }
    }

    public enum Currency {

        HKD {
            @Override
            public String toString() {
                return "344";
            }
        },
        USD {
            @Override
            public String toString() {
                return "840";
            }
        },
        SGD {
            @Override
            public String toString() {
                return "702";
            }
        },
        RMB {
            @Override
            public String toString() {
                return "156";
            }
        },
        YEN {
            @Override
            public String toString() {
                return "392";
            }
        },

        TWD {
            @Override
            public String toString() {
                return "901";
            }
        },
        AUD {
            @Override
            public String toString() {
                return "036";
            }
        },
        EUR {
            @Override
            public String toString() {
                return "978";
            }
        },
        GBP {
            @Override
            public String toString() {
                return "826";
            }
        },
        CAD {
            @Override
            public String toString() {
                return "124";
            }
        },
        MOP {
            @Override
            public String toString() {
                return "446";
            }
        },
        PHP {
            @Override
            public String toString() {
                return "608";
            }
        },
        THB {
            @Override
            public String toString() {
                return "764";
            }
        },
        MYR {
            @Override
            public String toString() {
                return "458";
            }
        },
        IDR {
            @Override
            public String toString() {
                return "360";
            }
        },
        KRW{
            @Override
            public String toString() {
                return "410";
            }
        },
        BND {
            @Override
            public String toString() {
                return "096";
            }
        },
        NZD {
            @Override
            public String toString() {
                return "554";
            }
        },
       SAR{
           @Override
           public String toString() {
               return "682";
           }
        },
        AED {
            @Override
            public String toString() {
                return "784";
            }
        },
        BRL {
            @Override
            public String toString() {
                return "986";
            }
        },
        INR {
            @Override
            public String toString() {
                return "356";
            }
        },
        TRY {
            @Override
            public String toString() {
                return "949";
            }
        },
        ZAR {
            @Override
            public String toString() {
                return "710";
            }
        },
        VND {
            @Override
            public String toString() {
                return "704";
            }
        },
        DKK{
            @Override
            public String toString() {
                return "208";
            }
        },
        ILS{
            @Override
            public String toString() {
                return "376";
            }
        },
        NOK{
            @Override
            public String toString() {
                return "578";
            }
        },

        RUB{
            @Override
            public String toString() {
                return "643";
            }
        },
        SEK{
            @Override
            public String toString() {
                return "752";
            }
        },
        CHF{
            @Override
            public String toString() {
                return "756";
            }
        },
        ARS{
            @Override
            public String toString() {
                return "032";
            }
        },
        CLP{
            @Override
            public String toString() {
                return "152";
            }
        },
        COP{
            @Override
            public String toString() {
                return "170";
            }
        },
        CZK{
            @Override
            public String toString() {
                return "203";
            }
        },
        EGP{
            @Override
            public String toString() {
                return "818";
            }
        },
        HUF{
            @Override
            public String toString() {
                return "348";
            }
        },
        KZT{
            @Override
            public String toString() {
                return "398";
            }
        },
        LBP{
            @Override
            public String toString() {
                return "422";
            }
        },
        MXN{
            @Override
            public String toString() {
                return "484";
            }
        },
        NGN{
            @Override
            public String toString() {
                return "566";
            }
        },
        PKR{
            @Override
            public String toString() {
                return "586";
            }
        },
        PEN{
            @Override
            public String toString() {
                return "604";
            }
        },
        PLN{
            @Override
            public String toString() {
                return "985";
            }
        },
        QAR{
            @Override
            public String toString() {
                return "634";
            }
        },
        RON{
            @Override
            public String toString() {
                return "946";
            }
        },
        UAH{
            @Override
            public String toString() {
                return "980";
            }
        },
        VEF{
            @Override
            public String toString() {
                return "937";
            }
        },
        LKR{
            @Override
            public String toString() {
                return "144";
            }
        },
        KWD{
            @Override
            public String toString() {
                return "414";
            }
        },
        JPY {
            @Override
            public String toString() {
                return "392";
            }
        },
        NULL {
            @Override
            public String toString() {
                return null;
            }
        }
    }

    public enum PayType {
        NORMAL_PAYMENT {
            @Override
            public String toString() {
                return "N";
            }
        },
        HOLD_PAYMENT {
            @Override
            public String toString() {
                return "H";
            }
        }

    }

    public enum PayMethod {

        ALIPAY {
            @Override
            public String toString() {
                return "ALIPAYOFFL";
            }
        }, ALIPAY_HK {
            @Override
            public String toString() {
                return "ALIPAYHKOFFL";
            }
        }, BOOST {
            @Override
            public String toString() {
                return "BOOSTOFFL";
            }
        }, GCASH {
            @Override
            public String toString() {
                return "GCASHOFFL";
            }
        }, GRABPAY {
            @Override
            public String toString() {
                return "GRABPAYOFFL";
            }
        }, OEPAY {
            @Override
            public String toString() {
                return "OEPAYOFFL";
            }
        }, PROMPTPAY {
            @Override
            public String toString() {
                return "PROMPTPAYOFFL";
            }
        }, UNIONPAY {
            @Override
            public String toString() {
                return "UNIONPAYOFFL";
            }
        }, WECHATPAY {
            @Override
            public String toString() {
                return "WECHATOFFL";
            }
        }, WECHAT_HK {
            @Override
            public String toString() {
                return "WECHATHKOFFL";
            }
        }, MASTER {
            @Override
            public String toString() {
                return "Master";
            }
        }, VISA {
            @Override
            public String toString() {
                return "VISA";
            }
        }
    }

    public enum Payment {
        SCAN_QR{
            @Override
            public String toString() {
                return "scanqr";
            }
        }, PRESENT_QR{
            @Override
            public String toString() {
                return "presentqr";
            }
        }, CARD{
            @Override
            public String toString() {
                return "card";
            }
        }
    }

    public enum SortOrder {
        ASC{
            @Override
            public String toString() {
                return "asc";
            }
        }, DESC{
            @Override
            public String toString() {
                return "desc";
            }
        }
    }

    public enum OrderStatus {
        ACCEPTED{
            @Override
            public String toString() {
                return "Accepted";
            }
        }, REJECTED{
            @Override
            public String toString() {
                return "Rejected";
            }
        }, PENDING{
            @Override
            public String toString() {
                return "Pending";
            }
        }, REFUNDED{
            @Override
            public String toString() {
                return "Refunded";
            }
        }, PARTIAL_REFUNDED{
            @Override
            public String toString() {
                return "PartialRefunded";
            }
        }, CANCELLED{
            @Override
            public String toString() {
                return "Cancelled";
            }
        }, ACCEPTED_ADJ{
            @Override
            public String toString(){
                return "Accepted_Adj";
            }
        }, ALL{
            @Override
            public String toString() {
                return "";
            }
        }
    }

    public enum TxnAction {
        VOID{
            @Override
            public String toString() {
                return "Void";
            }
        }, REFUND{
            @Override
            public String toString() {
                return "OnlineRefund";
            }
        }, PARTIAL_REFUND{
            @Override
            public String toString() {
                return "OnlinePartialRefund";
            }
        }
    }

    public enum PayBankId {
        FIRST_DATA{
            @Override
            public String toString() {
                return "First-Data";
            }
        }
    }

    public enum FDRequest {
        SALE{
            @Override
            public String toString() {
                return "createTxn";
            }
        }, VOID{
            @Override
            public String toString() {
                return "voidTxn";
            }
        }, SETTLEMENT{
            @Override
            public String toString() {
                return "settlementTxn";
            }
        }, REPRINT {
            @Override
            public String toString() {
                return "";
            }
        }, UPDATE_TXN_ACCEPTED{
            @Override
            public String toString() {
                return "updateTxnAccepted";
            }
        }, UPDATE_TXN_REJECTED{
            @Override
            public String toString() {
                return "updateTxnRejected";
            }
        }, UPDATE_TXN_CANCELLED{
            @Override
            public String toString() {
                return "updateTxnCancelled";
            }
        }, UPDATE_FAILED_TXN{
            @Override
            public String toString() {
                return "updateFailedTxn";
            }
        }
    }

    public enum EnvType {
        PRODUCTION{
            @Override
            public String toString() {
                return "Production";
            }
        },SANDBOX{
            @Override
            public String toString() {
                return "Sandbox";
            }
        },SIT{
            @Override
            public String toString() {
                return "Sit";
            }
        }
    }

    public enum PayChannel {

        WEBVIEW {
            @Override
            public String toString() {
                return "WebView";
            }
        }, DIRECT {
            @Override
            public String toString() {
                return "Direct";
            }
        }, Indirect {
            @Override
            public String toString() {
                return "Indirect";
            }
        }

    }

    public enum Language {
        ENGLISH {
            @Override
            public String toString() {
                return "E";
            }
        }, CHINESE_TRADITIONAL {
            @Override
            public String toString() {
                return "C";
            }
        }, CHINESE_SIMPLIFIED {
            @Override
            public String toString() {
                return "X";
            }
        }, JAPANESE {
            @Override
            public String toString() {
                return "J";
            }
        }, THAI {
            @Override
            public String toString() {
                return "T";
            }
        }, FRENCH {
            @Override
            public String toString() {
                return "F";
            }
        }, GERMAN {
            @Override
            public String toString() {
                return "G";
            }
        }, RUSSIAN {
            @Override
            public String toString() {
                return "R";
            }
        },
        SPANISH {
            @Override
            public String toString() {
                return "S";
            }
        },
        VIETNAMESE {
            @Override
            public String toString() {
                return "V";
            }
        },

    }

    public enum SecureMethod {
        SHA_1 {
            @Override
            public String toString() {
                return "SHA-1";
            }
        },
        //SHA_2,
        MD5 {
            @Override
            public String toString() {
                return "MD5";
            }
        }
    }

    /*public enum EWallet{
        SAMSUNG{
            @Override
            public String toString() {
                return "SAMSUNG";
            }
        }
    }*/

    public enum ServiceType {

        INAPP {
            @Override
            public String toString() {
                return "INAPP_PAYMENT";
            }
        },
        APP2APP {
            @Override
            public String toString() {
                return "APP2APP";
            }
        },
        WEB {
            @Override
            public String toString() {
                return "WEB_PAYMENT";
            }
        },
        MOBILE_WEB {
            @Override
            public String toString() {
                return "MOBILEWEB_PAYMENT";
            }
        },
    }
    public enum AmountFormat{
        TOTAL_PRICE{
            @Override
            public String toString() {
                return "FORMAT_TOTAL_PRICE_ONLY";
            }
        },
        TOTAL_FREE_TEX{
            @Override
            public String toString() {
                return "FORMAT_TOTAL_FREE_TEXT_ONLY";
            }
        },
        TOTAL_AMOUNT_PENDING{
            @Override
            public String toString() {
                return "FORMAT_TOTAL_AMOUNT_PENDING_TEXT_ONLY";
            }
        },
        TOTAL_PENDING_TEXT{
            @Override
            public String toString() {
                return "FORMAT_TOTAL_PENDING_TEXT_ONLY";
            }
        },
    }

    public enum Cryptogram{
        NONE{
            @Override
            public String toString() {
                return "NONE";
            }
        },
        UCAF{
            @Override
            public String toString() {
                return "UCAF";
            }
        },
        ICC{
            @Override
            public String toString() {
                return "ICC";
            }
        },
    }

    public enum Brand{

        AMERICANEXPRESS{
            @Override
            public String toString() {
                return "AMERICANEXPRESS";
            }
        },
        MASTERCARD{
            @Override
            public String toString() {
                return "MASTERCARD";
            }
        },
        VISA{
            @Override
            public String toString() {
                return "VISA";
            }
        },
        DISCOVER{
            @Override
            public String toString() {
                return "DISCOVER";
            }
        },
        CHINAUNIONPAY{
            @Override
            public String toString() {
                return "CHINAUNIONPAY";
            }
        },
        UNKNOWN_CARD{
            @Override
            public String toString() {
                return "UNKNOWN_CARD";
            }
        },
        OCTOPUS{
            @Override
            public String toString() {
                return "OCTOPUS";
            }
        },
        ECI{
            @Override
            public String toString() {
                return "ECI";
            }
        }


    }
}

