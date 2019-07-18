//
//  HLAwsiotNetRequestObject.h
//  AFNetworking
//
//  Created by jiafangrong on 2019/7/18.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface HLAwsiotNetRequestObject : NSObject

@property(nonatomic,copy,readonly) NSString *url;
@property(nonatomic,strong,readonly) NSDictionary *params;
@property(nonatomic,copy,readonly) NSString *sv;

@property(nonatomic,copy)NSString * _Nullable accessToken;
@property(nonatomic,copy) NSString * _Nullable serverType;

@end

NS_ASSUME_NONNULL_END


@interface HLRequestAwsiotConfig : HLAwsiotNetRequestObject

 

@end
