//
//  HLAwsiotNetRequestObject.m
//  AFNetworking
//
//  Created by jiafangrong on 2019/7/18.
//

#import "HLAwsiotNetRequestObject.h"

@implementation HLAwsiotNetRequestObject

@end


@implementation HLRequestAwsiotConfig

-(NSString*)url{
    return @"/app/v2/cognito/user_identity/get";
}
-(NSString*)sv{
    return @"sv";
}
-(NSDictionary*)params{
    return @{};
}

@end
