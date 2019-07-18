//
//  HLAwsiotNetRequestManager.m
//  AFNetworking
//
//  Created by jiafangrong on 2019/7/18.
//

#import "HLAwsiotNetRequestManager.h"



#define ios_sc @"01dd431d098546f9baf5233724fa2ee2"

@interface HLAwsiotNetRequestManager()

@property(nonatomic,strong)  AFHTTPSessionManager *mySessionManager;

@end

@implementation HLAwsiotNetRequestManager

+ (instancetype)sharedManager {
    static HLAwsiotNetRequestManager *manager = nil;
    static dispatch_once_t once;
    dispatch_once(&once, ^{
        manager = [[self alloc] init];
        
    });
    return manager;
}
-(instancetype)init{
    self = [super init];
    if (self) {
        self.mySessionManager = [AFHTTPSessionManager manager];
        self.mySessionManager.requestSerializer.timeoutInterval = 30;
        /*
         manager.responseSerializer = [AFHTTPResponseSerializer serializer]; // AFN不会解析,数据是data，需要自己解析
         manager.requestSerializer = [AFHTTPRequestSerializer serializer]; // 上传普通格式
         */
        self.mySessionManager.responseSerializer.acceptableContentTypes =  [NSSet setWithObjects:@"application/json", @"text/html",@"text/json",@"text/javascript", nil];
        // 开始设置请求头
        [self.mySessionManager.requestSerializer setValue:@"application/json" forHTTPHeaderField:@"Content-Type"];
        self.mySessionManager.requestSerializer = [AFJSONRequestSerializer serializer]; // 上传JSON格式
        self.mySessionManager.responseSerializer = [AFJSONResponseSerializer serializer]; // AFN会JSON解析返回的数据
    }
    return self;
}

-(NSDictionary*)defaultParameter{
    
    NSString* version = [[[NSBundle mainBundle] infoDictionary] objectForKey:@"CFBundleShortVersionString"];
    NSString* bundleID=[[[NSBundle mainBundle] infoDictionary] objectForKey:(NSString*) kCFBundleIdentifierKey];
    NSString            *appVersion        = [NSString stringWithFormat:@"%@___%@",bundleID,version];
    
    NSTimeInterval        timer            = [[NSDate date] timeIntervalSince1970];
    NSNumber            *timerNubmer    = [NSNumber numberWithLongLong:timer*1000];

    NSUUID *deviceID = [[UIDevice currentDevice] identifierForVendor];
    NSString* macString = [deviceID UUIDString];
    
    return @{@"sc":ios_sc,@"app_ver":appVersion,@"ts":timerNubmer,@"phone_id":macString,@"app_version":version};
}

-(NSDictionary*)makeRequestDictionarySv:(NSString*)sv
                            accessToken:(NSString*)token
                          withParameter:(NSDictionary*)params{
    
    NSMutableDictionary *morePara = [NSMutableDictionary dictionaryWithDictionary:[self defaultParameter]];
    NSLog(@"sv %@ - params %@",sv,params);
    [morePara setObject:sv forKey:@"sv"];
    [morePara setObject:token forKey:@"access_token"];
    if (params) {
        NSString *key;
        for (key in params) {
            [morePara setObject:[params objectForKey:key] forKey:key];
        }
    }
    return morePara;
}

-(NSString*)makeRequestUrl:(NSString*)url withBaseUrl:(NSString*)baseUrl{
    
    return [NSString stringWithFormat:@"%@%@",baseUrl,url];
    
}

- (void)sendRequest:(HLAwsiotNetRequestObject *)request
            success:(void(^)(id object))success
            failure:(void(^)(NSError* error))failure{
    NSString *baseUrl = @"https://api.wyzecam.com:8443";
    if (request.serverType.integerValue==1) {
       baseUrl = @"https://beta-api.wyzecam.com:8443";
    }else if (request.serverType.integerValue==2){
        baseUrl = @"https://test-api.wyzecam.com:8443";
    }
    NSString *url = [self makeRequestUrl:request.url withBaseUrl:baseUrl];
    NSLog(@"request_url %@",url);
    NSDictionary *moreParameter = [self makeRequestDictionarySv:request.sv accessToken:request.accessToken withParameter:request.params];
    
    dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0), ^{
        
        [self.mySessionManager POST:url parameters:moreParameter  success:^(NSURLSessionDataTask * _Nonnull task, id  _Nullable responseObject) {
            dispatch_async(dispatch_get_main_queue(), ^{
                NSLog(@"responseObject %@---url %@",responseObject,url);
                success(responseObject);
            });
            
        } failure:^(NSURLSessionDataTask * _Nullable task, NSError * _Nonnull error) {
            dispatch_async(dispatch_get_main_queue(), ^{
                NSLog(@"error %@",error);
                failure(error);
            });
            
        }];
    });
}


@end
