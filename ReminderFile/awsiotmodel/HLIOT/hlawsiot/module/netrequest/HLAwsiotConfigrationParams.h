//
//  HLAwsiotConfigrationParams.h
//  AFNetworking
//
//  Created by jiafangrong on 2019/7/19.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface HLAwsiotConfigrationParams : NSObject

@property(nonatomic,copy) NSString *serverType;
@property(nonatomic,copy) NSString *accessToken;

@property(nonatomic,copy) NSString *awsRegion;
@property(nonatomic,copy) NSString *awsIdentityPoolid;
@property(nonatomic,copy) NSString *awsIotEndPoint;
@property(nonatomic,copy) NSString *awsIotLoginClientId;
@property(nonatomic,copy) NSString *awsIotIdentityid;
@property(nonatomic,copy) NSString *awsIotToken;

@end

NS_ASSUME_NONNULL_END
