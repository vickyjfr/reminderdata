//
//  HLAwsiotClient.m
//  AFNetworking
//
//  Created by jiafangrong on 2019/7/17.
//

#import "HLAwsiotClient.h"
#import "AWSCore.h"
#import "HLAwsiotSyncMutableDictionary.h"

static HLAwsiotSyncMutableDictionary *_iotSeviceClient = nil;

@interface HLAwsiotClient()

@end

@implementation HLAwsiotClient

+(void)addAwsDDLog{
    [AWSDDLog sharedInstance].logLevel = AWSDDLogLevelDebug;
    [AWSDDLog addLogger:[AWSDDTTYLogger sharedInstance]];
}

-(instancetype)init{
    if (self = [super init]) {
    }
    return self;
}

+(void)creatIotServiceClient{
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        _iotSeviceClient = [HLAwsiotSyncMutableDictionary new];
    });
}

+(void)registerHLIotDataManagerWithAccessToken:(NSString*)token
                                    serverType:(NSString*)serverType
                                        forKey:(NSString*)key{
    
}

@end
